<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>

<h2><bean:message bundle="JOB_BANK_RESOURCES" key="title.jobBank.searchStudents"/></h2>

<fr:form> 
	<fr:edit id="searchStudents" name="searchStudents" >
		<fr:schema type="module.jobBank.domain.beans.SearchStudents" bundle="JOB_BANK_RESOURCES">
			<fr:slot name="person" layout="autoComplete" key="label.manager.person" bundle="JOB_BANK_RESOURCES">
		        <fr:property name="labelField" value="name"/>
				<fr:property name="format" value="${name} (${user.username})"/>
				<fr:property name="minChars" value="3"/>		
				<fr:property name="args" value="provider=module.jobBank.presentationTier.providers.StudentsAutoCompleteProvider"/>
				<fr:property name="size" value="40"/>
			</fr:slot>
			<fr:slot name="degree" key="label.enterprise.offer.degree" layout="menu-select">
				<fr:property name="providerClass" value="module.jobBank.presentationTier.providers.RemoteAllDegreesProvider" />
				<fr:property name="eachLayout" value="values" />
				<fr:property name="eachSchema" value="jobBank.enterprise.jobOffer.remoteDegree" />
				<fr:property name="sortBy" value="presentationName=asc" />
			</fr:slot>
			<fr:slot name="registrationConclued" key="label.enterprise.offer.degree" />
			
		</fr:schema>
		
		<fr:layout name="tabular">
			<fr:property name="classes" value="form"/>
			<fr:property name="columnClasses" value=",,tderror"/>
		</fr:layout>
	
	</fr:edit>
	<p> 
		<html:submit styleClass="inputbutton">
			<bean:message  bundle="JOB_BANK_RESOURCES" key="button.jobBank.search"/>
		</html:submit>
	</p>
</fr:form>

<fr:view name="results">
		<fr:schema type="myorg.domain.User" bundle="JOB_BANK_RESOURCES">
			<fr:slot name="user.username" key="label.curriculum.id" bundle="JOB_BANK_RESOURCES"/>
			<fr:slot name="name" key="label.manager.person.name" bundle="JOB_BANK_RESOURCES"/>
			<fr:slot name="student.presentationDegreeName" key="label.curriculumQualification.degree" bundle="JOB_BANK_RESOURCES"/>
		</fr:schema>
		<fr:layout name="tabular">
			<fr:property name="classes" value="tstyle2"/>
			<fr:property name="columnClasses" value="aleft,,,,aright,"/>
			<fr:property name="sortBy" value="person=asc"/>
			
			<fr:property name="link(view)" value="/enterprise.do?method=viewStudentCurriculum"/>
			<fr:property name="bundle(view)" value="JOB_BANK_RESOURCES"/>
			<fr:property name="key(view)" value="link.jobBank.view"/>
			<fr:property name="param(view)" value="student.externalId/studentOID" />
			<fr:property name="order(view)" value="1"/>
		</fr:layout>
</fr:view>

