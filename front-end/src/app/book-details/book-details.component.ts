import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Book } from 'src/app/book';
import { BooksService } from '../books.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit{
  book: Book | undefined;

  constructor(
    private route: ActivatedRoute,
    private bookService: BooksService,
    private location: Location
  ){}

  ngOnInit(): void{
    this.getBook();
  }

  getBook(): void{
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.bookService.getBook(id)
    .subscribe(book => this.book = book);
  }

  goBack(): void{
    this.location.back();
  }

  save(): void{
    if(this.book){
      this.bookService.updateBook(this.book)
      .subscribe(() => this.goBack());
    }
  }
}
