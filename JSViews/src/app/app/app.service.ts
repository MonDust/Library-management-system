import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Library} from '../models/library.model';
import {Book} from '../models/book.model';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private apiURL =  'http://gateway:8080';

  constructor(private httpClient: HttpClient) {
  }

  getAllLibraries(): Observable<Library[]> {
    return this.httpClient.get<Library[]>(`${this.apiURL}/libraries/`);
  }

  getLibraryById(id: string): Observable<Library> {
    return this.httpClient.get<Library>(`${this.apiURL}/libraries/${id}/`);
  }

  createLibrary(library: Library): Observable<Library> {
    return this.httpClient.post<Library>(`${this.apiURL}/libraries/`, library);
  }

  updateLibrary(library: Library): Observable<Library> {
    return this.httpClient.put<Library>(`${this.apiURL}/libraries/${library.id}/`, library);
  }

  deleteLibrary(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiURL}/libraries/${id}/`);
  }

  getAllBooks(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(`${this.apiURL}/books/`);
  }

  getBookById(id: string): Observable<Book> {
    return this.httpClient.get<Book>(`${this.apiURL}/books/${id}/`);
  }

  getBooksByLibraryId(libraryId: string): Observable<Book[]> {
    return this.httpClient.get<Book[]>(`${this.apiURL}/libraries/${libraryId}/books/`);
  }

  createBook(libraryId: string, book: Book): Observable<Book> {
    return this.httpClient.post<Book>(`${this.apiURL}/libraries/${libraryId}/books/`, book);

  }

  updateBook(book: Book): Observable<Book> {
    return this.httpClient.put<Book>(`${this.apiURL}/books/${book.id}/`, book);
  }

  deleteBook(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiURL}/books/${id}/`);
  }
}
