import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSlotItemComponent } from './admin-slot-item.component';

describe('AdminSlotItemComponent', () => {
  let component: AdminSlotItemComponent;
  let fixture: ComponentFixture<AdminSlotItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminSlotItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSlotItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
