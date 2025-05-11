import {Component, OnInit} from '@angular/core';
import {AppService} from '../../app/app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Library} from '../../models/library.model';
import {catchError, EMPTY, tap} from 'rxjs';
import {LibraryFormComponent} from '../../forms/library-form/library-form.component';

@Component({
  selector: 'app-edit-library-view',
  standalone: true,
  imports: [ LibraryFormComponent ],
  templateUrl: './edit-library-view.component.html',
  styleUrl: './edit-library-view.component.css'
})
export class EditLibraryViewComponent implements OnInit {
  constructor(
    private service: AppService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  library: Library | undefined;
  message: string = '';

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.service.getLibraryById(params['id'])
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
    if (this.library == undefined) {
      return;
    }
    this.service.updateLibrary(this.library)
      .pipe(
        catchError((error) => {
          this.message = error.statusText;
          return EMPTY;
        }),
        tap((library: Library) => {
          this.router.navigate(['/libraries', library.id]);
        })
      )
      .subscribe();
  }
}
