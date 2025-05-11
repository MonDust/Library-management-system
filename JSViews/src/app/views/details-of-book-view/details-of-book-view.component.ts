import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import { catchError, EMPTY, tap } from 'rxjs';
import {AppService} from '../../app/app.service';
import {Book} from '../../models/book.model';
import { Library } from '../../models/library.model';

@Component({
  selector: 'app-details-of-book-view',
  imports: [ RouterLink ],
  templateUrl: './details-of-book-view.component.html',
  styleUrl: './details-of-book-view.component.css'
})
export class DetailsOfBookViewComponent {
  constructor(
    private appService: AppService,
    private route: ActivatedRoute
  ) {}

  message: string = '';
  library: Library | undefined;
  book: Book | undefined;

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.appService.getBookById(params['bookId'])
        .pipe(
          catchError(error => {
            this.message = `Book: ${error.statusText}`;
            return EMPTY;
          }),
          tap((book) => {
            this.book = book;
          })
        )
        .subscribe();
      this.appService.getLibraryById(params['id'])
        .pipe(
          catchError(error => {
            this.message = `Library: ${error.statusText}`;
            return EMPTY;
          }),
          tap((library) => {
            this.library = library;
          })
        )
        .subscribe();
    });
  }

}
