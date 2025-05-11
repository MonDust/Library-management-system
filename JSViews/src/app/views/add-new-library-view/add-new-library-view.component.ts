import { Component } from '@angular/core';
import {LibraryFormComponent} from '../../forms/library-form/library-form.component';
import {AppService} from '../../app/app.service';
import {Router} from '@angular/router';
import {catchError, EMPTY, tap} from 'rxjs';
import {Library} from '../../models/library.model';

@Component({
  selector: 'app-add-new-library-view',
  standalone: true,
  imports: [ LibraryFormComponent ],
  templateUrl: './add-new-library-view.component.html',
  styleUrl: './add-new-library-view.component.css'
})
export class AddNewLibraryViewComponent {
  constructor(private service: AppService, private router: Router) {}

  message: string = '';

  onSubmit(library: Library): void {
    this.message = '';
    if (!library.name) {
      return;
    }
    this.service.createLibrary(library)
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
