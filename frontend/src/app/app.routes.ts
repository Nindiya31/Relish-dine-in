import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home';
import { Menu } from './pages/menu/menu';
import { Services } from './pages/services/services';
import { Contact} from './pages/contact/contact';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'menu', component: Menu },
  { path: 'services', component: Services },
  { path: 'contact', component: Contact },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];