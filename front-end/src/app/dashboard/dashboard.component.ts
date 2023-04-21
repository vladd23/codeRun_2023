import { Component, OnInit } from '@angular/core';
import {Book} from '../book';
import { BooksService } from '../books.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  books: Book[] = [];

  constructor(private bookService: BooksService){ }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void{
    this.bookService.getBooks()
    .subscribe(books => this.books = books.slice(1, 5));
  }
}
