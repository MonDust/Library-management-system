import { Component, EventEmitter, Input, Output } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Book} from '../../models/book.model';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './book-form.component.html',
  styleUrl: './book-form.component.css'
})
export class BookFormComponent {
  @Input() book: Book = {
    id: '',
    name: '',
    numberOfPages: 0,
    libraryId: ''
  }

  @Output() submit = new EventEmitter<Book>();
  @Input() message: string = '';

  onSubmit(): void {
    if (this.book) {
      this.submit.emit(this.book);
    }
  }
}
