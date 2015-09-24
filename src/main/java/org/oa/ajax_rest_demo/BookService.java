package org.oa.ajax_rest_demo;

import org.oa.ajax_rest_demo.dao.StorageSession;
import org.oa.ajax_rest_demo.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookService {

    @GET
    @Produces("application/json")
    public Response getAll() {
        StorageSession session = StorageSession.getInstance();

        List<Book> books = session.getBookDao().loadAll();
        return Response.ok(books,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Produces("application/json")
    public Response add(@FormParam("name") String name,
                        @FormParam("year") int year,
                        @FormParam("author") String author) {
        StorageSession session = StorageSession.getInstance();

        Book newBook = session.getBookDao().create(new Book(
                name, year, author
        ));
        return Response.ok(newBook,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Book book) {
        StorageSession session = StorageSession.getInstance();

        Book newBook = session.getBookDao().update(book);
        return Response.ok(newBook,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @DELETE
    @Produces("application/json")
    public Response delete(@PathParam("id") long id) {
        StorageSession session = StorageSession.getInstance();

        Book book = session.getBookDao().findById(id);

        session.getBookDao().delete(book);

        return Response.ok(book,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
