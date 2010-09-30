package module.jobBank.domain.utils;

import module.jobBank.domain.JobBankSystem;
import myorg.util.BundleUtil;
import pt.ist.fenixWebFramework.rendererExtensions.util.IPresentableEnum;

public enum MobilityProcessStageState implements IPresentableEnum {
    NOT_YET_UNDER_WAY, UNDER_WAY, COMPLETED;

    private static final String BUNDLE = JobBankSystem.JOB_BANK_RESOURCES;
    private static final String KEY_PREFIX = "label.MobilityProcessStageState.";

    public String getLocalizedName() {
	final String key = KEY_PREFIX + name();
	return BundleUtil.getStringFromResourceBundle(BUNDLE, key);
    }
}
