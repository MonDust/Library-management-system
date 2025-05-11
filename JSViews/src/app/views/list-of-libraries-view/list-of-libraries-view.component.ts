import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import {AppService} from '../../app/app.service';
import {Library} from '../../models/library.model';

@Component({
  selector: 'app-list-of-libraries-view',
  imports: [ RouterLink ],
  templateUrl: './list-of-libraries-view.component.html',
  styleUrl: './list-of-libraries-view.component.css'
})
export class ListOfLibrariesViewComponent implements OnInit {
  constructor(private service: AppService) { }

  libraries: Library[] | undefined;

  ngOnInit(): void {
    this.service.getAllLibraries().subscribe(libraries => {
      this.libraries = libraries;
    });
  }

  deleteLibrary(id: string): void {
    this.service.deleteLibrary(id).subscribe(() => {
      this.libraries = this.libraries?.filter(librarie => librarie.id !== id);
    });
  }
}
