public class ProblematicTenantException extends Exception {

    public ProblematicTenantException(Person person) {
        super(
                "Osoba "
                + person.toString()
                + " posiadała już najem pomieszczen: "
                + person.getRentedRooms().toString().replaceAll("[\\[\\]]", "")
        );
    }
}

