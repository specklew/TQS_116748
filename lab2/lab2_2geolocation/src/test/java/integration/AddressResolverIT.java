package integration;

import geolocation.Address;
import geolocation.AddressResolver;
import geolocation.ISimpleHttpClient;
import geolocation.TqsBasicHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddressResolverIT {

    ISimpleHttpClient httpClient = new TqsBasicHttpClient();
    AddressResolver addressResolver = new AddressResolver(httpClient);
    Optional<Address> address = Optional.of(new Address(
            "802 Arkenstone Dr",
            "Jacksonville",
            "FL",
            "32225",
            null));

    @Test
    public void testGoodCoordinates() throws IOException, URISyntaxException, ParseException {
        assertThat(addressResolver.findAddressForLocation(30.333472, -81.470448), equalTo(address));
    }

    @Test
    public void testBadCoordinates() throws IOException, URISyntaxException, ParseException {
        assertThat(addressResolver.findAddressForLocation(100.0, 100.0), equalTo(Optional.empty()));
    }

}
