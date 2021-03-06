<%@ include file="/WEB-INF/view/module/mdrtb/include.jsp" %>
<%--
showIfSet=true/false (defaults to true)
	Whether or not to show important ids that are set
showIfMissing=true/false (defaults to true)
	Whether or not to show (and allow entry of) important ids that are missing
highlightMissing=true/false (defaults to true)
	Whether or not to highlight missing important ids
--%>

<openmrs:htmlInclude file="/scripts/easyAjax.js" />
<openmrs:htmlInclude file="/dwr/interface/DWRPatientService.js" />
<openmrs:htmlInclude file="/dwr/engine.js" />
<openmrs:htmlInclude file="/dwr/util.js" />

<c:set var="showIfSet" value="${empty model.showIfSet || model.showIfSet == 'true'}"/>
<c:set var="showIfMissing" value="${empty model.showIfMissing || model.showIfMissing == 'true'}"/>
<c:set var="highlightMissing" value="${empty model.highlightMissing || model.highlightMissing == 'true'}"/>

<openmrs:globalProperty var="identifierTypes" key="mdrtb.patient_identifier_type" />

<script type="text/javascript">
	var currIndex = 0;

	function handleSaveIdentifier(index) {
		currIndex = index;
		var identifierType = DWRUtil.getValue('identifierType_' + index);
		var identifier = DWRUtil.getValue('identifier_' + index);
		//alert("id is " + identifier + " and type is " + identifierType);
		var identifierLocationId = DWRUtil.getValue('identifierLocationId_' + index);
		oldId = identifier;
		oldLocation = identifierLocationId;
		if (identifierType != null && identifierType != '' && identifier != null && identifier != '')
			DWRPatientService.addIdentifier(${model.patientId}, identifierType, identifier, identifierLocationId, finishSave);
	}
	
	function finishSave(data) {
		//alert("getting here with data: " + data);
		if ( data ) {
			if ( data.length > 0 ) {
				displayIdError(currIndex, data);
				document.getElementById("identifier_" + currIndex).select();
				document.getElementById("identifier_" + currIndex).focus();
			} else {
				refreshPage();
			}
		} else {
			refreshPage();
		}
	}
	
	function displayIdError(index, msg) {
		DWRUtil.setValue("msg_" + index, getMessage(msg));
		if ( msg.length > 0 ) {
			document.getElementById("msg_" + index).style.display = "";
		} else {
			document.getElementById("msg_" + index).style.display = "none";
		}		
	}
	
	function getMessage(msg) {
		var ret = "";
	
		if ( msg == "PatientIdentifier.error.formatInvalid" ) ret = "<spring:message code="PatientIdentifier.error.formatInvalid" />";
		if ( msg == "PatientIdentifier.error.checkDigit" ) ret = "<spring:message code="PatientIdentifier.error.checkDigit" />";
		if ( msg == "PatientIdentifier.error.notUnique" ) ret = "<spring:message code="PatientIdentifier.error.notUnique" />";
		if ( msg == "PatientIdentifier.error.duplicate" ) ret = "<spring:message code="PatientIdentifier.error.duplicate" />";
		if ( msg == "PatientIdentifier.error.insufficientIdentifiers" ) ret = "<spring:message code="PatientIdentifier.error.insufficientIdentifiers" />";
		if ( msg == "PatientIdentifier.error.general" ) ret = "<spring:message code="PatientIdentifier.error.general" />";
		
		return ret;
	}
	
	function identifierFieldChanged(index) {
		var id = DWRUtil.getValue('identifier_' + index);
		if (id == null || id == '') {
			document.getElementById('idSaveButton_' + index).disabled = true;
		} else {
			document.getElementById('idSaveButton_' + index).disabled = false;
		}
	}
</script>
<c:set var="headerShown" scope="page" value="0" />
<table cellspacing="0" cellpadding="2">
	<c:forTokens var="idHighlightAndLocation" items="${identifierTypes}" delims="," varStatus="iter">
		<c:set var="temp" value="${fn:split(idHighlightAndLocation, ':')}" />
		<c:set var="idTypeName" value="${temp[0]}"/>
		<c:set var="highlight" value="${temp[1]}"/>
		<c:set var="locationName" value="${temp[2]}"/>
		<c:set var="location" value="${model.locationsByName[locationName]}"/>
	
		<c:set var="found" value="${null}" />
		<c:set var="id_err" value="${null}" />
		
		<!-- TESTING:  -->
		
		<c:forEach var="identifier" items="${model.patient.identifiers}">
			<c:if test="${!identifier.voided && identifier.identifierType.name == idTypeName}">
				<c:set var="found" value="${identifier}"/>
				<c:set var="id_err" value="${model.identifierErrors[identifier]}" />
			</c:if>
		</c:forEach>

		<c:if test="${ (found!=null && showIfSet) || (found==null && showIfMissing) }">
			<c:if test='${headerShown == "0"}'>
				<Tr><Td>&nbsp;</Td><Td>Identifier</td><td>Location</Td><td>&nbsp;</td></Tr>
				<c:set var="headerShown" scope="page" value="1" />
			</c:if>
			<tr <c:if test="${found==null && highlightMissing && highlight=='true'}"> class="highlighted"</c:if>>
				<td>${idTypeName}:</td>
				<td>
					<input type="hidden" id="identifierType_${iter.index}" value="${idTypeName}" />
					<c:if test="${found!=null}">
						<input type="text" id="identifier_${iter.index}" onKeyUp="identifierFieldChanged(${iter.index})" value="${found.identifier}" />
						</td><td>
						<openmrs:fieldGen
							type="org.openmrs.Location"
							formFieldName="identifierLocationId_${iter.index}"
							val="${found.location}"
						/>
					</c:if>
					<c:if test="${found==null}">
						<input type="text" id="identifier_${iter.index}" onKeyUp="identifierFieldChanged(${iter.index})" />
						</td><td>
						<openmrs:fieldGen
							type="org.openmrs.Location"
							formFieldName="identifierLocationId_${iter.index}"
							val=""
						/>
					</c:if>
					<input id="idSaveButton_${iter.index}" type="button" value="<spring:message code="general.save" />" disabled="true" onClick="handleSaveIdentifier(${iter.index})"/>
				</td>
				<td>
					<c:if test="${id_err == null}">
						<span id="msg_${iter.index}" style="display:none;" class="error"></span>
					</c:if>
					<c:if test="${id_err != null}">
						<span id="msg_${iter.index}" class="error"><spring:message code="${id_err}" /></span>
					</c:if>
				</td>
			</tr>
		</c:if>		
	</c:forTokens>
</table>
