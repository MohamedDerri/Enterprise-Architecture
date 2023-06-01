import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;



import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("1337","future is loading", 18.00, "med derri");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .when()
                .get("books/1337")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("1337"))
                .body("title",equalTo("future is loading"))
                .body("price",equalTo(18.00f))
                .body("author",equalTo("med derri"));
        //cleanup
        given()
                .when()
                .delete("books/1337");
    }
    @Test
    public void testDeleteBook(){
//        add book
        Book book = new Book("1337","future is loading", 18.00, "med derri");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .when()
                .delete("books/1337");

        given()
                .when()
                .get("books")
                .then()
                .body("books",hasSize(0));
    }

    @Test
    public void testAddBook(){
        Book book = new Book("1337","future is loading", 18.00, "med derri");

        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .when()
                .get("books")
                .then()
                .body("books",hasSize(1));

        given()
                .when()
                .delete("books/1337");
    }

    @Test
    public void testUpdateBooks(){
        Book book = new Book("1337","future is loading", 18.00, "med derri");
        Book book1 = new Book("7331","future is not loading", 9.00, "dem irred");

        given()
                .contentType("application/json")
                .body(book)
                .when().post("books").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book1)
                .when().put("/books/"+book1.getIsbn()).then()
                .statusCode(200);

        given()
                .when()
                .get("books/7331")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("title",equalTo("future is not loading"))
                .body("price",equalTo(9.0f))
                .body("author",equalTo("dem irred"));


        given()
                .when()
                .delete("books/7331");

    }
}

