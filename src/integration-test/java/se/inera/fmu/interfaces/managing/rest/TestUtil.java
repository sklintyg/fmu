package se.inera.fmu.interfaces.managing.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import se.inera.fmu.domain.model.authentication.Role;
import se.inera.fmu.domain.model.authentication.User;
import se.inera.fmu.infrastructure.security.FakeAuthenticationProvider;
import se.inera.fmu.infrastructure.security.FakeAuthenticationToken;
import se.inera.fmu.infrastructure.security.FakeCredentials;
import se.inera.fmu.infrastructure.security.FmuUserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for testing REST controllers.
 */
public class TestUtil {

    /** MediaType for JSON UTF8 */
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /**
     * Convert an object to JSON byte array.
     *
     * @param object
     *            the object to convert
     * @return the JSON byte array
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    
    /**
     * Log in with a fake user with predefined credentials
     */
    public static void loginWithNoActiveRole(FakeAuthenticationProvider provider) {
		/*User user = new User();
        user.setFirstName("Åsa");
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.ROLE_SAMORDNARE);
        roles.add(Role.ROLE_UTREDARE);
		user.setRoles(roles);
		user.setMiddleAndLastName("Andersson");
		user.setVardenhetHsaId("IFV1239877878-1045");
		user.setHsaId("IFV1239877878-1042");
		FmuUserDetails details = new FmuUserDetails(user);
		FakeCredentials credencial = new FakeCredentials("IFV1239877878-1042", "Åsa", "Andersson", true, "IFV1239877878-1045");
		Authentication authentication = new UsernamePasswordAuthenticationToken(details, credencial);
		SecurityContextHolder.getContext().setAuthentication(authentication);*/
    	
    	FakeCredentials cred = new FakeCredentials("IFV1239877878-1042", "Åsa", "Andersson", true, "IFV1239877878-1045");
    	FakeAuthenticationToken token = new FakeAuthenticationToken(cred);
    	
    	Authentication auth = provider.authenticate(token);
    	
    	FmuUserDetails details = (FmuUserDetails) auth.getDetails();
    	details.getUser().getRoles().add(Role.ROLE_SAMORDNARE);
    	
    	SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
