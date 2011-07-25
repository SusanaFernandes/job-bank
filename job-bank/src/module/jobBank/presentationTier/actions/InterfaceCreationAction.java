package module.jobBank.presentationTier.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.jobBank.domain.groups.EnterpriseActiveGroup;
import module.jobBank.domain.groups.EnterpriseGroup;
import module.jobBank.domain.groups.NpeGroup;
import module.jobBank.domain.groups.StudentActiveGroup;
import module.jobBank.domain.groups.StudentGroup;
import myorg.domain.RoleType;
import myorg.domain.VirtualHost;
import myorg.domain.contents.ActionNode;
import myorg.domain.contents.Node;
import myorg.domain.groups.AnyoneGroup;
import myorg.domain.groups.IntersectionGroup;
import myorg.domain.groups.NegationGroup;
import myorg.domain.groups.PersistentGroup;
import myorg.domain.groups.Role;
import myorg.domain.groups.UserGroup;
import myorg.presentationTier.actions.ContextBaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.fenixWebFramework.servlets.functionalities.CreateNodeAction;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@Mapping(path = "/jobBankInterfaceCreationAction")
public class InterfaceCreationAction extends ContextBaseAction {

    @CreateNodeAction(bundle = "JOB_BANK_RESOURCES", key = "add.node.jobBank.interface", groupKey = "label.module.jobBank")
    public final ActionForward createAnnouncmentNodes(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) throws Exception {
	final VirtualHost virtualHost = getDomainObject(request, "virtualHostToManageId");
	final Node node = getDomainObject(request, "parentOfNodesToManageId");

	final PersistentGroup npeGroup = NpeGroup.getInstance();
	final PersistentGroup entrepriseGroup = EnterpriseGroup.getInstance();
	final PersistentGroup entrepriseActiveGroup = EnterpriseActiveGroup.getInstance();
	final PersistentGroup studentGroup = StudentGroup.getInstance();
	final StudentActiveGroup studentActiveGroup = StudentActiveGroup.getInstance();

	ActionNode homeNode = ActionNode.createActionNode(virtualHost, node, "/jobBank", "frontPage",
		"resources.JobBankResources", "link.sideBar.jobBank", AnyoneGroup.getInstance());

	ActionNode.createActionNode(virtualHost, homeNode, "/jobBank", "frontPage", "resources.JobBankResources",
		"link.sideBar.jobBank.home", AnyoneGroup.getInstance());

	ActionNode.createActionNode(virtualHost, homeNode, "/jobBank", "manageRoles", "resources.JobBankResources",
		"link.sideBar.jobBank.manageRoles", Role.getRole(RoleType.MANAGER));

	ActionNode.createActionNode(virtualHost, homeNode, "/backOffice", "configuration", "resources.JobBankResources",
		"link.sideBar.jobBank.configuration", Role.getRole(RoleType.MANAGER));

	/* Student */
	final IntersectionGroup candidateStudentsToJobBank = IntersectionGroup.createIntersectionGroup(
		NegationGroup.createNegationGroup(studentActiveGroup), NegationGroup.createNegationGroup(npeGroup), studentGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/student", "termsResponsibilityStudent",
		"resources.JobBankResources", "link.sideBar.jobBank.createStudent", candidateStudentsToJobBank);

	ActionNode.createActionNode(virtualHost, homeNode, "/student", "personalArea", "resources.JobBankResources",
		"link.sideBar.jobBank.personalArea", studentActiveGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/student", "searchOffers", "resources.JobBankResources",
		"link.sideBar.jobBank.offers", studentActiveGroup);

	/* End Student */

	/* Enterprise */

	ActionNode.createActionNode(virtualHost, homeNode, "/enterprise", "termsResponsibilityEnterprise",
		"resources.JobBankResources", "link.sideBar.jobBank.createEnterprise",
		NegationGroup.createNegationGroup(UserGroup.getInstance()));

	ActionNode.createActionNode(virtualHost, homeNode, "/enterprise", "enterprise", "resources.JobBankResources",
		"link.sideBar.jobBank.enterprise", entrepriseGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/enterprise", "viewAllJobOffers", "resources.JobBankResources",
		"link.sideBar.jobBank.offers", entrepriseActiveGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/enterprise", "searchStudents", "resources.JobBankResources",
		"link.sideBar.jobBank.searchStudents", entrepriseActiveGroup);

	/* End Enterprise */

	/* Back Office */

	ActionNode.createActionNode(virtualHost, homeNode, "/backOffice", "enterprises", "resources.JobBankResources",
		"link.sideBar.jobBank.viewEnterprises", npeGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/backOffice", "jobOffers", "resources.JobBankResources",
		"link.sideBar.jobBank.offers", npeGroup);

	ActionNode.createActionNode(virtualHost, homeNode, "/backOffice", "searchStudents", "resources.JobBankResources",
		"link.sideBar.jobBank.searchStudents", npeGroup);

	/* End Back Office */

	return forwardToMuneConfiguration(request, virtualHost, node);
    }
}
