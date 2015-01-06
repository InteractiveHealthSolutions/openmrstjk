package org.openmrs.module.dotsreports.reporting.definition;

import java.util.Date;

import org.openmrs.module.dotsreports.MdrtbConstants.DrugSetType;
import org.openmrs.module.reporting.cohort.definition.BaseCohortDefinition;
import org.openmrs.module.reporting.common.Localized;
import org.openmrs.module.reporting.definition.configuration.ConfigurationProperty;

@Localized("dotsreports.reporting.TreatmentStartedCohortDefinition")
public class TreatmentStartedCohortDefinition extends BaseCohortDefinition {

	 private static final long serialVersionUID = 1L;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date fromDate;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date toDate;
		
		private DrugSetType drugSet;
		
		//private String drugSet;
		
		//***** CONSTRUCTORS *****

		/**
		 * Default Constructor
		 */
		public TreatmentStartedCohortDefinition() {
			super();
			
		}
		
		//***** INSTANCE METHODS *****
		
		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return super.toString();
		}
		
		//***** PROPERTY ACCESS *****

		/**
		 * @return the fromDate
		 */
		public Date getFromDate() {
			return fromDate;
		}

		/**
		 * @param fromDate the fromDate to set
		 */
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		/**
		 * @return the toDate
		 */
		public Date getToDate() {
			return toDate;
		}

		/**
		 * @param toDate the toDate to set
		 */
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		public DrugSetType getDrugSet() {
			return drugSet;
		}
	
		public void setDrugSet(DrugSetType drugSet) {
			this.drugSet = drugSet;
		}
}
