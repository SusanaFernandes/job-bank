package module.jobBank.domain.beans;

import java.util.HashSet;
import java.util.Set;

import module.jobBank.domain.FenixDegree;
import module.jobBank.domain.Student;
import module.jobBank.domain.utils.IPredicate;
import module.organization.domain.Person;
import net.sourceforge.fenixedu.domain.RemoteDegree;

public class SearchStudents extends SearchUsers {

    private boolean registrationConclued;
    private FenixDegree degree;

    public SearchStudents() {
	super();
	setRegistrationConclued(registrationConclued);
    }

    @Override
    public Set<Person> search() {
	Set<Person> persons = super.search();
	if (persons != null && !persons.isEmpty()) {
	    return persons;
	}
	return new SearchResult(readPersonsToSatisfizedPredicate());
    }

    private Set<Person> readPersonsToSatisfizedPredicate() {
	final Set<Person> results = new HashSet<Person>();
	Student.readAllStudents(new IPredicate<Student>() {
	    @Override
	    public boolean evaluate(Student student) {
		if (isSatisfiedRegistrationProcess(student) && isSatisfiedDegree(student)) {
		    results.add(student.getPerson());
		    return true;
		}
		return false;
	    }
	});
	return results;

    }

    private boolean isSatisfiedRegistrationProcess(Student student) {
	return !hasSelectedRegistrationConlued() || student.isConcludedProcessed();
    }

    private boolean isSatisfiedDegree(Student student) {
	RemoteDegree degree = student.getDegree();
	return !hasSelectedDegree() || degree != null && degree.equals(getDegree());
    }

    public boolean isRegistrationConclued() {
	return registrationConclued;
    }

    public void setRegistrationConclued(boolean completedDegree) {
	this.registrationConclued = completedDegree;
    }

    public void setDegree(FenixDegree degree) {
	this.degree = degree;
    }

    public FenixDegree getDegree() {
	return degree;
    }

    public boolean hasSelectedDegree() {
	return getDegree() != null;
    }

    public boolean hasSelectedRegistrationConlued() {
	return isRegistrationConclued();
    }
}
