import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import {Book} from './book';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService{
  createDb(){
    const books = [
      {id: 1, nume: 'Teo', publisherList: ['Maier', 'Vlad']},
      {id: 2, nume: 'Vlad', publisherList: ['Teo', 'Maier']},
      {id: 3, nume: 'Maier', publisherList: ['Teo', 'Vlad']}
    ];
    return {books};
  }
  
  genId(books: Book[]): number{
    return books.length > 0 ? Math.max(...books.map(book => book.id)) + 1 : 11;
  }
}
