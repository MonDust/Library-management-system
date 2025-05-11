import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNewBookViewComponent } from 'views/add-new-book-view/add-new-book-view.component';
import { AddNewLibraryViewComponent } from 'views/add-new-library-view/add-new-library-view.component';
import { DetailsOfBookViewComponent } from 'views/details-of-book-view/details-of-book-view.component';
import { DetailsOfLibraryViewComponent } from 'views/details-of-library-view/details-of-library-view.component';
import { EditBookViewComponent } from 'views/edit-book-view/edit-book-view.component';
import { EditLibraryViewComponent } from 'views/edit-library-view/edit-library-view.component';
import { ListOfLibrariesViewComponent } from 'views/list-of-libraries-view/list-of-libraries-view.component';

export const routes: Routes = [
  {path: 'libraries/:id/books/add', component: AddNewBookViewComponent},
  {path: 'libraries/add', component: AddNewLibraryViewComponent},
  {path: 'libraries/:id/edit', component: EditLibraryViewComponent},
  {path: 'libraries', component: ListOfLibrariesViewComponent},
  {path: 'libraries/:id/books/:bookId/edit', component: EditBookViewComponent},
  {path: 'libraries/:id', component: DetailsOfLibraryViewComponent},
  {path: 'libraries/:id/books/:bookId', component: DetailsOfBookViewComponent},
  {path: '**', component: ListOfLibrariesViewComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
