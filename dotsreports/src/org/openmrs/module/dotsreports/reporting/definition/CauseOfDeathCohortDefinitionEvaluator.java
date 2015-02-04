package org.openmrs.module.dotsreports.reporting.definition;

import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.dotsreports.MdrtbConstants.CauseOfDeathType;
import org.openmrs.module.dotsreports.TbConcepts;
import org.openmrs.module.dotsreports.reporting.MdrtbQueryService;
import org.openmrs.module.dotsreports.service.TbService;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
@Handler(supports={CauseOfDeathCohortDefinition.class})
public class CauseOfDeathCohortDefinitionEvaluator implements CohortDefinitionEvaluator{

	/***
	 * The method uses no dates from the CohortDefinition since we are interested
	 * in died ever and not specific to a date range.
	 */
	@Override
	public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context)
			throws EvaluationException {
		CauseOfDeathCohortDefinition cd = (CauseOfDeathCohortDefinition)cohortDefinition;
		Cohort c=null;
		
		
		if(cd.getCauseType() == CauseOfDeathType.CAUSE_TB)
		{
			c = MdrtbQueryService. getPatientsDiedByTB(context, null, null);
		}
		else if(cd.getCauseType()==CauseOfDeathType.CAUSE_TBHIV)
		{
			Concept tbHivDeath = Context.getService(TbService.class).getConcept(TbConcepts.DEATH_BY_TBHIV[0]);
			c = MdrtbQueryService.getPatientsByCauseOfDeath(context, null, null, tbHivDeath);
		}
		else if(cd.getCauseType() ==CauseOfDeathType.CAUSE_NONTB)
		{
			Concept nonTbDeath = Context.getService(TbService.class).getConcept(TbConcepts.DEATH_BY_OTHER_DISEASES[0]);
			c = MdrtbQueryService.getPatientsByCauseOfDeath(context, null, null, nonTbDeath);
		}
		
		return c; 
	}

}
