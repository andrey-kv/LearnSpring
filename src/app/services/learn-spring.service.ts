import { Contact } from './../models/contact';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin' : '*'
    })
  };

@Injectable({
  providedIn: 'root'
})
export class LearnSpringService {

  private apiUrl = 'contacts/';

  constructor(private http: HttpClient) { }

  public async getContacts(): Promise<Contact[]> {
    return this.http.get<Contact[]>(this.apiUrl, httpOptions).toPromise();
  }
}
