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
import { RouletteComponent } from './components/roulette/roulette.component';
import { SlotComponent } from './components/slot/slot.component';
import { SlotpageComponent } from './pages/slotpage/slotpage.component';
import { AddBalanceComponent } from './pages/add-balance/add-balance.component';
import { AdminCaseComponent } from './components/admin/admin-case/admin-case.component';
import { AdminSlotComponent } from './components/admin/admin-slot/admin-slot.component';
import { AdminCaseItemComponent } from './components/admin/admin-case-item/admin-case-item.component';
import { AdminSlotItemComponent } from './components/admin/admin-slot-item/admin-slot-item.component';
import { AdminCaseListComponent } from './components/admin/admin-case-list/admin-case-list.component';
import { AdminSlotListComponent } from './components/admin/admin-slot-list/admin-slot-list.component';
import { CrashComponent } from './components/crash/crash.component';
import { CoinflipComponent } from './components/coinflip/coinflip.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    HomeComponent,
    CaseComponent,
    InventoryComponent,
    CrashComponent,
    CoinflipComponent,
    RouletteComponent,
    SlotComponent,
    SlotpageComponent,
    AddBalanceComponent,
    AdminCaseComponent,
    AdminSlotComponent,
    AdminCaseItemComponent,
    AdminSlotItemComponent,
    AdminCaseListComponent,
    AdminSlotListComponent,
    AdminHomeComponent
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
