import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { BooksService } from '../books.service';


@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
}
)
export class BooksComponent implements OnInit{
  books: Book[] = [];

  constructor(private bookService: BooksService){ }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void{
    this.bookService.getBooks()
    .subscribe(books => this.books = books);
  }

  add(name: string): void{
    name = name.trim();
    if(!name) {return;}
    this.bookService.addBook({ name } as unknown as Book)
    .subscribe(book => {
      this.books.push(book);
    });
  }

  delete(book: Book): void{
    this.books = this.books.filter(b => b !== book);
    this.bookService.deleteBook(book.id).subscribe();
  }
}
