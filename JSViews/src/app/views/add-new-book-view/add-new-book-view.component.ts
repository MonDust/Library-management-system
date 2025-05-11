import {Component, OnInit} from '@angular/core';
import {BookFormComponent} from '../../forms/book-form/book-form.component';
import {AppService} from '../../app/app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {catchError, EMPTY, tap} from 'rxjs';
import {Library} from '../../models/library.model';
import {Book} from '../../models/book.model';

@Component({
  selector: 'app-add-new-book-view',
  standalone: true,
  imports: [ BookFormComponent ],
  templateUrl: './add-new-book-view.component.html',
  styleUrl: './add-new-book-view.component.css'
})
export class AddNewBookViewComponent implements OnInit {
  constructor(
    private appService: AppService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  message: string = '';
  library: Library | undefined;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.appService.getLibraryById(params['id'])
        .pipe(
          catchError((error) => {
            this.message = error.statusText;
            return EMPTY;
          }),
          tap((library: Library) => {
            this.library = library;
          })
        ).subscribe();
    })
  }

  onSubmit(book: Book): void {
    this.message = '';
    if (!this.library || !book.name) {
      return;
    }
    this.appService.createBook(this.library.id, book)
      .pipe(
        catchError((error) => {
          this.message = error.statusText;
          return EMPTY;
        }),
        tap((book: Book) => {
          this.router.navigate(['/libraries', this.library?.id, 'books', book.id]);
        })
      )
      .subscribe();
  }

}
