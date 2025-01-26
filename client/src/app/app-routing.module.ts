import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/components/login/login/login.component';
import { RegisterComponent } from './auth/components/register/register/register.component';
import { NavbarComponent } from './nav/navbar/navbar.component';
import { HomeComponent } from './home/home/home.component';

const routes: Routes = [
  {path:"login", component: LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"", component:HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
