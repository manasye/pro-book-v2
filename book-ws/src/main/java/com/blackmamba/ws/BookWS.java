package com.blackmamba.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;
import com.blackmamba.api.BankAPI;
import com.blackmamba.api.GoogleBookAPI;
import com.blackmamba.model.ListBooks;
import com.blackmamba.model.BookDetail;
import com.blackmamba.model.TransactionResponse;

@WebService
@SOAPBinding(style = Style.RPC, use = SOAPBinding.Use.ENCODED)
public class BookWS {
    @WebMethod
    public ListBooks searchTitle(String title) {
        GoogleBookAPI googleBookAPI = new GoogleBookAPI();
        List<BookDetail> books = googleBookAPI.searchBookByTitle(title);
        ListBooks bookList = new ListBooks(books);
        return bookList;
    }

    @WebMethod
    public BookDetail searchDetail(String id) {
        System.out.println("SEARCH DEATAIL");
        GoogleBookAPI googleBook = new GoogleBookAPI();
        BookDetail bookDetail = googleBook.getBookDetail(id);
        return bookDetail;
    }

    @WebMethod
    public ListBooks getBookRecommendation(String[] categories) {
        GoogleBookAPI googleBookAPI = new GoogleBookAPI();
        List<BookDetail> bookRecommendations = googleBookAPI.getBookRecommendation(categories);
        if (bookRecommendations == null) {
            return null;
        } else {
            ListBooks bookRecommendationsList = new ListBooks(bookRecommendations);
            return bookRecommendationsList;
        }
    }

    // TODO: implement book transaction
    @WebMethod
    public TransactionResponse buyBook(String cardNumber, String token, String bookId, int bookQuantity) {
        BankAPI bankAPI = new BankAPI();
        TransactionResponse transactionResponse = bankAPI.makeTransaction(cardNumber, token, bookId, bookQuantity);
        return transactionResponse;
    }

}
