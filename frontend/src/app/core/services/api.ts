import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/env';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  createCustomer(data: any) {
    return this.http.post(`${this.baseUrl}/customers/create`, data);
  }

  getAllCustomers() {
    return this.http.get(`${this.baseUrl}/customers`);
  }
}