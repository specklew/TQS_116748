package geolocation;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

    @Mock
    ISimpleHttpClient httpClient = Mockito.mock(ISimpleHttpClient.class);

    @InjectMocks
    AddressResolver addressResolver = new AddressResolver(httpClient);

    String exampleResponse = """
            {
              "info": {
                "statuscode": 0,
                "copyright": {
                  "text": "© 2023 MapQuest, Inc.",
                  "imageUrl": "http://api.mqcdn.com/res/mqlogo.gif",
                  "imageAltText": "© 2023 MapQuest, Inc."
                },
                "messages": []
              },
              "options": {
                "maxResults": 1,
                "thumbMaps": true,
                "ignoreLatLngInput": false
              },
              "results": [
                {
                  "providedLocation": {
                    "latLng": {
                      "lat": 30.333472,
                      "lng": -81.470448
                    }
                  },
                  "locations": [
                    {
                      "street": "12714 Ashley Melisse Blvd",
                      "adminArea6": "",
                      "adminArea6Type": "Neighborhood",
                      "adminArea5": "Jacksonville",
                      "adminArea5Type": "City",
                      "adminArea4": "Duval",
                      "adminArea4Type": "County",
                      "adminArea3": "FL",
                      "adminArea3Type": "State",
                      "adminArea1": "US",
                      "adminArea1Type": "Country",
                      "postalCode": "32225",
                      "geocodeQualityCode": "L1AAA",
                      "geocodeQuality": "ADDRESS",
                      "dragPoint": false,
                      "sideOfStreet": "R",
                      "linkId": "0",
                      "unknownInput": "",
                      "type": "s",
                      "latLng": {
                        "lat": 30.33472,
                        "lng": -81.470448
                      },
                      "displayLatLng": {
                        "lat": 30.333472,
                        "lng": -81.470448
                      },
                      "mapUrl": "http://www.mapquestapi.com/staticmap/v4/getmap?key=KEY&type=map&size=225,160&pois=purple-1,30.3334721,-81.4704483,0,0,|&center=30.3334721,-81.4704483&zoom=15&rand=-553163060",
                      "nearestIntersection": {
                        "streetDisplayName": "Posey Cir",
                        "distanceMeters": "851755.1608527573",
                        "latLng": {
                          "longitude": -87.523761,
                          "latitude": 35.013434
                        },
                        "label": "Danley Rd & Posey Cir"
                      },
                      "roadMetadata": {
                        "speedLimitUnits": "mph",
                        "tollRoad": null,
                        "speedLimit": 40
                      }
                    }
                  ]
                }
              ]
            }

            """;

    String exampleEmpty = """
            {
              "info": {
                "statuscode": 0,
                "copyright": {
                  "text": "© 2023 MapQuest, Inc.",
                  "imageUrl": "http://api.mqcdn.com/res/mqlogo.gif",
                  "imageAltText": "© 2023 MapQuest, Inc."
                },
                "messages": []
              },
              "options": {
                "maxResults": 1,
                "thumbMaps": true,
                "ignoreLatLngInput": false
              },
              "results": [
                {
                  "providedLocation": {
                    "latLng": {
                      "lat": 0.0,
                      "lng": -0.0
                    }
                  },
                  "locations": []
                }
              ]
            }

            """;

    String exampleFailed = """
            {
              "info": {
                "statuscode": 1
              }
            }

            """;
    Optional<Address> address = Optional.of(new Address(
            "12714 Ashley Melisse Blvd",
            "Jacksonville",
            "FL",
            "32225",
            null));

    @Test
    void testFindAddressForCorrectLocation() throws URISyntaxException, ParseException, IOException {
        when(httpClient.doHttpGet(any(String.class))).thenReturn(exampleResponse);
        assertThat(addressResolver.findAddressForLocation(30.333472, -81.470448), equalTo(address));

    }

    @Test
    void testFindAddressForIncorrectLocation() throws IOException, URISyntaxException, ParseException {
        when(httpClient.doHttpGet(any(String.class))).thenReturn(exampleEmpty);
        assertThat(addressResolver.findAddressForLocation(0.0, 0.0), equalTo(Optional.empty()));

    }

    @Test
    void testFindAddressForFailedRequest() throws IOException, URISyntaxException, ParseException {
        when(httpClient.doHttpGet(any(String.class))).thenReturn(exampleFailed);
        assertThat(addressResolver.findAddressForLocation(0.0, 0.0), equalTo(Optional.empty()));

    }
}