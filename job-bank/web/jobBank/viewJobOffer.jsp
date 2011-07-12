<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/workflow.tld" prefix="wf"%>

<h2><bean:message bundle="JOB_BANK_RESOURCES" key="title.jobBank.offer"/></h2>

<bean:define id="jobOffer" name="process" property="jobOffer"/>
<bean:define id="processId" name="process" property="externalId"/>




<h3 class="separator">
	<bean:message bundle="JOB_BANK_RESOURCES" key="label.enterprise.jobOffer.information"/>
</h3>

<p> 
<bean:size id="comments"  name="process" property="comments"/>		 
<span class="comments">
	<html:link page="/student.do?method=viewJobOfferComments" paramId="processId" paramName="process" paramProperty="externalId">
	<%= comments %>
	<logic:equal name="comments" value="1">
		<bean:message key="link.comment" bundle="WORKFLOW_RESOURCES"/>
	</logic:equal>
	<logic:notEqual name="comments" value="1">
		<bean:message key="link.comments" bundle="WORKFLOW_RESOURCES"/>
	</logic:notEqual>
	</html:link>
	<bean:define id="unreadComments" name="process" property="unreadCommentsForCurrentUser"/>
	<logic:notEmpty name="unreadComments">
		<bean:size id="count" name="unreadComments"/> (<%= count.toString() %> novos) 
	</logic:notEmpty>
</span>

</p>
<div class="infobox mvert1">
	<bean:message key="label.enterprise.name" bundle="JOB_BANK_RESOURCES"/>:
	<html:link action="/student.do?method=viewEnterprise" paramId="OID" paramName="jobOffer" paramProperty="enterprise.externalId">
		 <bean:write name="jobOffer" property="enterprise.name"/>
	</html:link> 

	<fr:view name="jobOffer">
		<fr:schema type="module.jobBank.domain.JobOffer" bundle="JOB_BANK_RESOURCES">
			<fr:slot name="jobOfferProcess.processIdentification" key="label.enterprise.jobOfferProcess.processIdentification"/>
			<fr:slot name="externalCandidacy" key="label.enterprise.jobOffer.candidacyType.externalCandidacy"/>
			<fr:slot name="creationDate" key="label.enterprise.offer.creationDate"/>
			<fr:slot name="reference" key="label.enterprise.jobOffer.reference"/>
			<fr:slot name="place" key="label.enterprise.jobOffer.place"/>
			<fr:slot name="jobOfferType" key="label.enterprise.jobOffer.jobType"/>
			<fr:slot name="descriptionOffer" key="label.enterprise.jobOffer.descriptionOffer" layout="longText"/>
			<fr:slot name="requirements" key="label.enterprise.jobOffer.requirements" layout="longText"/>
			<fr:slot name="beginDate" key="label.enterprise.offer.beginDate" layout="picker" validator="pt.ist.fenixWebFramework.rendererExtensions.validators.DateTimeValidator"/> 
			<fr:slot name="endDate" key="label.enterprise.offer.endDate" layout="picker" validator="pt.ist.fenixWebFramework.rendererExtensions.validators.DateTimeValidator"/>			
			
			<fr:layout name="tabular-nonNullValues">
				<fr:property name="classes" value="mvert05 thleft"/>
			</fr:layout>
		</fr:schema>
	</fr:view>
</div>




