import { Input, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { CaseComponent } from './components/case/case.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { CoinflipComponent } from './components/coinflip/coinflip/coinflip.component';
import { CrashComponent } from './components/crash/crash/crash.component';
import { RouletteComponent } from './components/roulette/roulette.component';
import { SlotComponent } from './components/slot/slot/slot.component';
import { SlotpageComponent } from './pages/slotpage/slotpage.component';
import { AddBalanceComponent } from './pages/add-balance/add-balance.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    HomeComponent,
    CaseComponent,
    InventoryComponent,
    CoinflipComponent,
    CrashComponent,
    RouletteComponent,
    SlotComponent,
    SlotpageComponent,
    AddBalanceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
