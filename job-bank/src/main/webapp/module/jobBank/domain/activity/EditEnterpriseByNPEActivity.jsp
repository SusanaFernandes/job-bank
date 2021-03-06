<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<bean:define id="processOID" name="process" property="externalId"/>
<bean:define id="activityName" name="information" property="activityName"/>

<logic:messagesPresent property="message" message="true">
	<div class="error1">
		<html:messages id="errorMessage" property="message" message="true"> 
			<span><fr:view name="errorMessage"/></span>
		</html:messages>
	</div>
</logic:messagesPresent>



<div class="forminline">

	<fr:form action="/backOffice.do?method=processEditEnterprise" encoding="multipart/form-data" >	 
		<html:hidden property="activity" value="<%=activityName.toString()%>"/>
		<html:hidden property="processId" value="<%=processOID.toString()%>"/>
	
		<h3 class="mtop30px"><bean:message key="label.enterprise.createEnterprise.enterprise" bundle="JOB_BANK_RESOURCES"/></h3>
	
		<fr:edit id="activityBean" name="information" >
			<fr:schema bundle="JOB_BANK_RESOURCES" type="module.jobBank.domain.activity.EnterpriseInformation">	
			<fr:slot name="enterpriseBean.logoInputStream" key="label.enterprise.logo" bundle="JOB_BANK_RESOURCES">
				<fr:property name="fileNameSlot" value="enterpriseBean.logoFilename" />
				<fr:property name="size" value="30"/>
			</fr:slot>
			<fr:slot name="enterpriseBean.name" key="label.enterprise.name">
				<fr:validator name="module.jobBank.presentationTier.validators.EnterpriseNameNotRegisteredValidator"/>
				<fr:validator name="pt.ist.fenixWebFramework.rendererExtensions.validators.RequiredMultiLanguageStringValidator"/>
				<fr:validator name="pt.ist.fenixWebFramework.renderers.validators.RequiredValidator"/>
				<fr:property name="size" value="50" />
			</fr:slot>
			<fr:slot name="enterpriseBean.contactPerson" key="label.enterprise.contactPerson" required="true"> 
				<fr:property name="size" value="50" />
			 </fr:slot>
			 <fr:slot name="enterpriseBean.nif" key="label.enterprise.nif" required="true"/>  	
			<fr:slot name="enterpriseBean.designation" key="label.enterprise.designation" layout="autoComplete"
					validator="pt.ist.fenixWebFramework.rendererExtensions.validators.RequiredAutoCompleteSelectionValidator">
       			<fr:property name="labelField" value="description"/>
				<fr:property name="format" value="${code} - ${description} <span style='color:gray'>(${group.code} - ${group.description})</span>"/>
				<fr:property name="minChars" value="2"/>
				<fr:property name="args" value="provider=module.jobBank.presentationTier.providers.EconomicActivityClassificationLeafProvider"/>
				<fr:property name="size" value="60"/>
			</fr:slot>
			<fr:slot name="enterpriseBean.summary" key="label.enterprise.summary"  layout="area">  
				<fr:property name="columns" value="60" />
				<fr:property name="rows" value="6" />
				<fr:validator name="pt.ist.fenixWebFramework.rendererExtensions.validators.MultiLanguageStringValidator"/>
			</fr:slot>
		</fr:schema>
			<fr:layout name="tabular">
				<fr:property name="classes" value="thwidth150px"/> 
				<fr:property name="requiredMarkShown" value="true" />
				<fr:property name="requiredMessageShown" value="false" />
			</fr:layout>
		</fr:edit>
		<br/>
		<html:submit styleClass="inputbutton">
			<bean:message  bundle="JOB_BANK_RESOURCES" key="button.jobBank.submit"/>
		</html:submit>
	</fr:form>
	
	
	<fr:form action='<%="/backOffice.do?method=Enterprise&OID="+processOID %>'>
		<html:submit styleClass="inputbutton cancel"><bean:message  bundle="JOB_BANK_RESOURCES" key="button.jobBank.cancel"/></html:submit>
	</fr:form>

</div>

