package module.jobBank.domain.activity;

import module.jobBank.domain.EnterpriseProcess;
import module.jobBank.domain.JobBankSystem;
import module.workflow.activities.ActivityInformation;
import module.workflow.activities.WorkflowActivity;
import myorg.util.BundleUtil;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public class EnterpriseApprovalInformation extends ApprovalInformation {

    private static final long serialVersionUID = 1L;

    private MultiLanguageString enterpriseName;

    public EnterpriseApprovalInformation(final EnterpriseProcess process,
	    WorkflowActivity<EnterpriseProcess, ? extends ActivityInformation<EnterpriseProcess>> activity,
	    MultiLanguageString enterpriseName) {
	super(process, activity);
	setEnterpriseName(enterpriseName);
	updateMessage();
    }

    @Override
    public void updateMessage() {
	StringBuilder body = new StringBuilder();
	body.append(enterpriseName);
	body.append(getBody());
	body.append(getSignature());
	setMessage(body.toString());
    }

    private String getBody() {
	String bundleName = "message.jobbank.enterprise.approval.email.body";

	if (!isApprove()) {
	    bundleName = "message.jobbank.enterprise.rejection.email.body";
	}

	return BundleUtil.getFormattedStringFromResourceBundle(JobBankSystem.JOB_BANK_RESOURCES, bundleName);
    }
    
    private String getSignature() {
	return BundleUtil.getFormattedStringFromResourceBundle(JobBankSystem.JOB_BANK_RESOURCES, "message.jobbank.ist.signature");
    }

    public void setEnterpriseName(MultiLanguageString enterpriseName) {
	this.enterpriseName = enterpriseName;
    }

    public MultiLanguageString getEnterpriseName() {
	return enterpriseName;
    }

}