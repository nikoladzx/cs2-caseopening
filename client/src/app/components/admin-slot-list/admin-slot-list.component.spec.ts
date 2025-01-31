import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSlotListComponent } from './admin-slot-list.component';

describe('AdminSlotListComponent', () => {
  let component: AdminSlotListComponent;
  let fixture: ComponentFixture<AdminSlotListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminSlotListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSlotListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
