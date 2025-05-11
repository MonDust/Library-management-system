import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,RouterLink} from '@angular/router';
import { catchError, EMPTY, tap } from 'rxjs';
import {AppService} from '../../app/app.service';
import {Library} from '../../models/library.model';
import {Book} from '../../models/book.model';

@Component({
  selector: 'app-details-of-library-view',
  standalone: true,
  imports: [ RouterLink ],
  templateUrl: './details-of-library-view.component.html',
  styleUrl: './details-of-library-view.component.css'
})
export class DetailsOfLibraryViewComponent {
  constructor(
    private appService: AppService,
    private route: ActivatedRoute,
  ) {}

  message: string = '';
  library: Library | undefined;
  books: Book[] | undefined;

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.appService.getLibraryById(params['id'])
        .pipe(
          catchError((error) => {
            this.message = error.statusText;
            return EMPTY;
          }),
          tap((library) => {
            this.library = library;
            this.fetchBooks();
          })
        )
        .subscribe();
    })
  }

  fetchBooks(): void {
    if (this.library) {
      this.appService.getBooksByLibraryId(this.library.id).subscribe({
        next: (books) => {
          this.books = books;
        },
        error: (error) => {
          this.message = error.error.message;
        }
      });
    }
  }

  deleteBook(bookId: string): void {
    if (this.library) {
      this.appService.deleteBook(bookId).subscribe({
        next: () => {
          this.fetchBooks();
        },
        error: (error) => {
          this.message = error.error.message;
        }
      });
    }
  }

}
