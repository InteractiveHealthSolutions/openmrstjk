package org.openmrs.module.mdrtb.reporting.definition;

import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.mdrtb.MdrtbConstants.DrugSetType;
import org.openmrs.module.mdrtb.TbConcepts;
import org.openmrs.module.mdrtb.reporting.MdrtbQueryService;
import org.openmrs.module.mdrtb.service.MdrtbService;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;

@Handler(supports={TreatmentStartedCohortDefinition.class})
public class TreatmentStartedCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	/**
	 * Default Constructor
	 */
	public TreatmentStartedCohortDefinitionEvaluator() {}
	
	/**
     * @see CohortDefinitionEvaluator#evaluateCohort(CohortDefinition, EvaluationContext)
     * @should return patients whose first TB regimen was during the passed period
     */
    public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) {	
    	TreatmentStartedCohortDefinition cd = (TreatmentStartedCohortDefinition) cohortDefinition;
    	Cohort c =null;
    	if(cd.getDrugSet()==DrugSetType.FLD)
    	{
    		Concept tbDrugSet = Context.getService(MdrtbService.class).getConcept(TbConcepts.FIRST_LINE_DRUGS[0]);
      		c=MdrtbQueryService.getPatientsFirstStartingDrugs(context, cd.getFromDate(), cd.getToDate(), tbDrugSet);
    	}
    	else if(cd.getDrugSet()==DrugSetType.SLD)
    	{
    		Concept tbDrugSet = Context.getService(MdrtbService.class).getConcept(TbConcepts.SECOND_LINE_DRUGS[0]);
    		c=MdrtbQueryService.getPatientsFirstStartingDrugs(context, cd.getFromDate(), cd.getToDate(), tbDrugSet);
    	}
    	return c;
	}
}