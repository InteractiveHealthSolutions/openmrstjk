package org.openmrs.module.dotsreports.reporting.definition;

import java.util.Date;

import org.openmrs.module.dotsreports.MdrtbConstants.CauseOfDeathType;
import org.openmrs.module.reporting.cohort.definition.BaseCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.Localized;
import org.openmrs.module.reporting.definition.configuration.ConfigurationProperty;

import sun.reflect.generics.tree.BaseType;
@Localized("dotsreports.reporting.CauseOfDeathCohortDefinition")
public class CauseOfDeathCohortDefinition extends BaseCohortDefinition{

	  private static final long serialVersionUID = 1L;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date fromDate;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date toDate;
		
		@ConfigurationProperty(group="deathCauseType")
		String causeType;
		
		//private String drugSet;
		
		//***** CONSTRUCTORS *****

		/**
		 * Default Constructor
		 */
		public CauseOfDeathCohortDefinition()
		{
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

		public String getCauseType() {
			return causeType;
		}

		public void setCauseType (String causeType) {
			this.causeType = causeType;
		}
	
}
