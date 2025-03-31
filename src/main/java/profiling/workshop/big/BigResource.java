package profiling.workshop.big;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import profiling.workshop.time.TimeService;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

@Path("/big")
public class BigResource {

    private static final String BIG_CONTENT;

    static {
        byte[] a = new byte[1024*1024];
        Arrays.fill(a, (byte) 'a');
        BIG_CONTENT = new String(a);
    }

    @RegisterForReflection
    public record Content(String content) { }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Content now() {
        return new Content(BIG_CONTENT);
    }
}
