import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { CaseComponent } from './components/case/case.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { CoinflipComponent } from './components/coinflip/coinflip/coinflip.component';
import { CrashComponent } from './components/crash/crash/crash.component';
import { RouletteComponent } from './components/roulette/roulette.component';
import { SlotpageComponent } from './pages/slotpage/slotpage.component';
import { SlotComponent } from './components/slot/slot/slot.component';
import { AddBalanceComponent } from './pages/add-balance/add-balance.component';
import { AdminCaseComponent } from './components/admin-case/admin-case.component';
import { AdminCaseItemComponent } from './components/admin-case-item/admin-case-item.component';
import { AdminSlotComponent } from './components/admin-slot/admin-slot.component';
import { AdminSlotItemComponent } from './components/admin-slot-item/admin-slot-item.component';
import { AdminSlotListComponent } from './components/admin-slot-list/admin-slot-list.component';
import { AdminCaseListComponent } from './components/admin-case-list/admin-case-list.component';

const routes: Routes = [
  {path:"login", component: LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"", component:HomeComponent},
  {path:"case/:id", component:CaseComponent},
  {path:"inventory", component:InventoryComponent},
  {path:"coinflip", component:CoinflipComponent},
  {path:"crash", component:CrashComponent},
  {path:"roulette", component:RouletteComponent},
  {path:"slots", component:SlotpageComponent},
  {path:"slots/:id", component:SlotComponent},
  {path:"add-balance", component:AddBalanceComponent},
  {path:"admin-addcase", component:AdminCaseComponent},
  {path:"admincases/:id", component:AdminCaseItemComponent},
  {path:"admin-addslot", component:AdminSlotComponent},
  {path:"adminslots/:id", component:AdminSlotItemComponent},
  {path:"adminslots", component:AdminSlotListComponent},
  {path:"admincases", component:AdminCaseListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
