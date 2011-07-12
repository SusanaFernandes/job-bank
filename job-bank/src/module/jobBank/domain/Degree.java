package module.jobBank.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.fenixedu.domain.RemoteDegree;
import net.sourceforge.fenixedu.domain.administrativeOffice.RemoteAdministrativeOffice;
import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.plugins.remote.domain.RemoteHost;

public class Degree {
    @Service
    public static Set<RemoteDegree> readRemoteDegreesSet() {
	JobBankSystem.getInstance();
	RemoteHost remoteHost = JobBankSystem.readRemoteHost();
	Collection<RemoteDegree> col = RemoteAdministrativeOffice.readDegreeAdministrativeOffice(remoteHost)
		.getAdministratedDegrees();
	return new HashSet<RemoteDegree>(col);
    }

    @Service
    public static List<RemoteDegree> readRemoteDegrees() {
	return new ArrayList<RemoteDegree>(readRemoteDegreesSet());
    }

    public static Boolean Contains(RemoteDegree remoteDegree) {
	return readRemoteDegreesSet().contains(remoteDegree);
    }

}