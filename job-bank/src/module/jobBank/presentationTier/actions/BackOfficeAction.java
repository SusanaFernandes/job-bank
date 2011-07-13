package module.jobBank.presentationTier.actions;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.jobBank.domain.Enterprise;
import module.jobBank.domain.EnterpriseProcess;
import module.jobBank.domain.JobBankSystem;
import module.jobBank.domain.activity.EnterpriseInformation;
import module.organization.domain.OrganizationalModel;
import module.organization.presentationTier.actions.OrganizationModelAction.OrganizationalModelChart;
import module.workflow.presentationTier.actions.ProcessManagement;
import myorg.domain.MyOrg;
import myorg.presentationTier.actions.ContextBaseAction;
import myorg.util.VariantBean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@Mapping(path = "/backOffice")
public class BackOfficeAction extends ContextBaseAction {

    public ActionForward jobOffers(final ActionMapping mapping, final ActionForm foStudentrm, final HttpServletRequest request,
	    final HttpServletResponse response) {
	JobBankSearchActionCommons commons = new JobBankSearchActionCommons();
	commons.processJobOffersSearch(request);
	return forward(request, "/jobBank/backOffice/jobOffers.jsp");
    }

    public ActionForward enterprises(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {
	JobBankSearchActionCommons commons = new JobBankSearchActionCommons();
	commons.processEnterprisesSearch(request);
	return forward(request, "/jobBank/backOffice/enterprises.jsp");
    }

    public ActionForward Enterprise(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {
	EnterpriseProcess process = getDomainObject(request, "OID");
	return ProcessManagement.forwardToProcess(process);
    }

    public ActionForward approveEnterprise(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {
	Enterprise enterprise = getDomainObject(request, "enterpriseOID");
	enterprise.changeAgreement(enterprise.getAgreementForApproval());
	return enterprises(mapping, form, request, response);
    }

    public ActionForward rejectEnterprise(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {
	Enterprise enterprise = getDomainObject(request, "enterpriseOID");
	enterprise.reject();
	return enterprises(mapping, form, request, response);
    }

    public ActionForward processEditEnterprise(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) {
	EnterpriseInformation information = getRenderedObject("activityBean");
	return ProcessManagement.forwardToActivity(information.getProcess(), information.getActivity());
    }

    /* Configuration */

    public ActionForward configuration(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {
	JobBankSystem jobBankSystem = JobBankSystem.getInstance();
	VariantBean beanUrlEmailValidation = getUrlEmailValidationBean();
	beanUrlEmailValidation.setString(jobBankSystem.getUrlEmailValidation());
	request.setAttribute("beanUrlEmailValidation", beanUrlEmailValidation);
	request.setAttribute("jobBankSystem", jobBankSystem);
	return forward(request, "/jobBank/backOffice/configuration.jsp");
    }

    public ActionForward updateUrlEmailValidation(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) {
	JobBankSystem jobBankSystem = JobBankSystem.getInstance();
	VariantBean variantBean = getRenderedObject("beanUrlEmailValidation");
	if (variantBean != null) {
	    jobBankSystem.setUrlEmailValidation(variantBean.getString());
	}
	return configuration(mapping, form, request, response);
    }

    public ActionForward prepareSelectOrganizationalModel(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) {
	viewModels(request);
	return forward(request, "/jobBank/backOffice/selectOrganizationalModel.jsp");
    }

    public ActionForward selectOrganizationalModel(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) {
	final JobBankSystem jobBankSystem = JobBankSystem.getInstance();
	final OrganizationalModel organizationalModel = getDomainObject(request, "organizationalModelOid");
	jobBankSystem.setOrganizationalModel(organizationalModel);
	return configuration(mapping, form, request, response);
    }

    public static void viewModels(final HttpServletRequest request) {
	final Set<OrganizationalModel> organizationalModels = new TreeSet<OrganizationalModel>(
		OrganizationalModel.COMPARATORY_BY_NAME);
	organizationalModels.addAll(MyOrg.getInstance().getOrganizationalModelsSet());
	request.setAttribute("organizationalModels", organizationalModels);
	final OrganizationalModelChart organizationalModelChart = new OrganizationalModelChart(organizationalModels);
	request.setAttribute("organizationalModelChart", organizationalModelChart);
    }

    private VariantBean getUrlEmailValidationBean() {
	VariantBean beanUrlEmailValidation = getRenderedObject("beanUrlEmailValidation");
	if (beanUrlEmailValidation == null) {
	    beanUrlEmailValidation = new VariantBean();
	}
	return beanUrlEmailValidation;
    }
    /* End Configuration */

}
