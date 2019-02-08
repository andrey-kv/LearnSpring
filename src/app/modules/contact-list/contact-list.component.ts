import { Contact } from './../../models/contact';
import { Component, OnInit } from '@angular/core';
import { LearnSpringService } from '../../services/learn-spring.service';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  contacts: Contact[];
  error;

  constructor(private service: LearnSpringService) { }

  async ngOnInit() {
    this.contacts = await this.service.getContacts();
    console.log(this.contacts);
  }

  testClick() {
    console.log(this.contacts);
  }
}
