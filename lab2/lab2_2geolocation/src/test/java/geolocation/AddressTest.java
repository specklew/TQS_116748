package geolocation;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

class AddressTest {

    Address address = new Address(
            "12714 Ashley Melisse Blvd",
            "Jacksonville",
            "FL",
            "32225",
            null);

    String addressString = String.format(
            "Address{road=%s,state=%s,city=%s,zip=%s,houseNumber=%s}",
            "12714 Ashley Melisse Blvd",
            "Jacksonville",
            "FL",
            "32225",
            null);

    @Test
    void testHashCode() {
        assertThat(address.hashCode(), is(101));
    }

    @Test
    void testEquals() {
        Address testAddress = new Address(
                "12714 Ashley Melisse Blvd",
                "Jacksonville",
                "FL",
                "32225",
                null);

        assertThat(address.equals(testAddress), is(true));
    }

    @Test
    void testToString() {
        assertThat(address.toString(), is(addressString));
    }
}