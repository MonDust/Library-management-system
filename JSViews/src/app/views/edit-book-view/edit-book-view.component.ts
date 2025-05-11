import {Component, OnInit} from '@angular/core';
import {BookFormComponent} from '../../forms/book-form/book-form.component';
import {AppService} from '../../app/app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Library} from '../../models/library.model';
import {catchError, EMPTY, tap} from 'rxjs';
import {Book} from '../../models/book.model';
import {LibraryFormComponent} from '../../forms/library-form/library-form.component';

@Component({
  selector: 'app-edit-book-view',
  standalone: true,
  imports: [BookFormComponent],
  templateUrl: './edit-book-view.component.html',
  styleUrl: './edit-book-view.component.css'
})
export class EditBookViewComponent implements OnInit {
  constructor(
    private appService: AppService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  message: string = '';
  library: Library | undefined;
  book: Book | undefined;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.appService.getBookById(params['bookid'])
        .pipe(
          catchError((error) => {
            this.message = error.statusText;
            return EMPTY;
          }),
          tap((book: Book) => {
            this.book = book;
          })
        )
      .subscribe();
      this.appService.getLibraryById(params['id'])
          .pipe(
            catchError((error) => {
              this.message = error.statusText;
              return EMPTY;
            }),
            tap((library: Library) => {
              this.library = library;
            })
          )
          .subscribe();
      });
  }

  onSubmit(): void {
    this.message = '';
    if (this.book) {
      this.appService.updateBook(this.book)
        .pipe(
          catchError((error) => {
            this.message = error.statusText;
            return EMPTY;
          }),
          tap((book: Book) => {
            this.router.navigate([
              '/libraries',
              this.library?.id,
              'books',
              book.id,
            ]);
          })
        )
        .subscribe();
    }
  }
}
