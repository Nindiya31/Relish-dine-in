import { Component } from '@angular/core';
import { ApiService } from '../../core/services/api';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent {

  name = '';
  mobile = '';

  customers: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loadCustomers();
  }

  createCustomer() {
    const payload = {
      name: this.name,
      mobile: this.mobile
    };

    this.api.createCustomer(payload).subscribe({
      next: (res: any) => {
        console.log('Created:', res);
        this.loadCustomers();
        this.name = '';
        this.mobile = '';
      },
      error: (err: any) => {
        console.error(err);
      }
    });
  }

  loadCustomers() {
    this.api.getAllCustomers().subscribe({
      next: (res: any) => {
        this.customers = res;
      },
      error: (err: any) => {
        console.error(err);
      }
    });
  }
}