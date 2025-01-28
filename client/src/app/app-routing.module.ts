import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { CaseComponent } from './components/case/case.component';
import { InventoryComponent } from './components/inventory/inventory.component';

const routes: Routes = [
  {path:"login", component: LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"", component:HomeComponent},
  {path:"case/:id", component:CaseComponent},
  {path:"inventory", component:InventoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
