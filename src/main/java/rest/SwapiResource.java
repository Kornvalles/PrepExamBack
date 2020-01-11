package rest;

import facades.ApiFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ndupo
 */
@Path("swapi")
public class SwapiResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwapiResource
     */
    public SwapiResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{\"msg\": \"Welcome to the Star Wars API!\"}";
    }
    
    @GET
    @Path("peoplelist")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPeopleList() {
        ApiFacade af = new ApiFacade();
        String url = "https://swapi.co/api/";
        List<String> l = new ArrayList();
        l.add("people/1/");
        l.add("people/2/");
        l.add("people/3/");
        l.add("people/4/");
        l.add("people/5/");
        return af.fetch(url, l).toString();
    }
    
        @GET
    @Path("planetlist")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPlanetList() {
        ApiFacade af = new ApiFacade();
        String url = "https://swapi.co/api/";
        List<String> l = new ArrayList();
        l.add("planets/1/");
        l.add("planets/2/");
        l.add("planets/3/");
        l.add("planets/4/");
        l.add("planets/5/");
        return af.fetch(url, l).toString();
    }
    
            @GET
    @Path("filmlist")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonStarshipList() {
        ApiFacade af = new ApiFacade();
        String url = "https://swapi.co/api/";
        List<String> l = new ArrayList();
        l.add("films/1/");
        l.add("films/2/");
        l.add("films/3/");
        l.add("films/4/");
        l.add("films/5/");
        return af.fetch(url, l).toString();
    }
    
        @GET
    @Path("demoperson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPerson() {
        ApiFacade af = new ApiFacade();
        String url = "https://swapi.co/api/people/1";
        return af.fetch(url);
    }

}

