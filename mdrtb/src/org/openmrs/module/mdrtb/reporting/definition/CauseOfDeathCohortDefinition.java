package org.openmrs.module.mdrtb.reporting.definition;

import java.util.Date;

import org.openmrs.module.mdrtb.MdrtbConstants.CauseOfDeathType;
import org.openmrs.module.reporting.cohort.definition.BaseCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.definition.configuration.ConfigurationProperty;

import sun.reflect.generics.tree.BaseType;

public class CauseOfDeathCohortDefinition extends BaseCohortDefinition{

	  private static final long serialVersionUID = 1L;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date fromDate;
		
		@ConfigurationProperty(group="startDateGroup")
		private Date toDate;
		
		private CauseOfDeathType causeType;
		
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

		public CauseOfDeathType getCauseType() {
			return causeType;
		}

		public void setCauseType(CauseOfDeathType causeType) {
			this.causeType = causeType;
		}
	
}
