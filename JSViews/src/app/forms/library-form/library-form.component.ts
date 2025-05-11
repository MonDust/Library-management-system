import { Component, EventEmitter, Input, Output } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Library} from '../../models/library.model';

@Component({
  selector: 'app-library-form',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './library-form.component.html',
  styleUrl: './library-form.component.css'
})
export class LibraryFormComponent {
  @Input() library: Library = {
    id: '',
    name: '',
    address: '',
    city: ''
  };
  @Output() submit = new EventEmitter<Library>();
  @Input() message: string = '';

  onSubmit(): void {
    if (this.library) {
      this.submit.emit(this.library);
    }
  }

}
