import { Component, OnInit } from '@angular/core';
import Slot from 'src/app/models/Slot';
import { AdminService } from 'src/app/services/admin/admin.service';
import { ImgService } from 'src/app/services/img/img.service';

@Component({
  selector: 'app-admin-slot',
  templateUrl: './admin-slot.component.html',
  styleUrls: ['./admin-slot.component.css']
})
export class AdminSlotComponent implements OnInit {
  slot: Slot = { name: '', imagepath: '', id: 0 };
  selectedFile: File | null = null;
  message: string | null = null;

  constructor(private adminService: AdminService, private imgService: ImgService) { }

  ngOnInit(): void { }

  onFileChange(event: any): void {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  onSubmit(event: Event): void {
    event.preventDefault();
    if (this.selectedFile) {
      this.imgService.uploadImage(this.selectedFile).subscribe({
        next: (imageUrl: string) => {
          console.log(imageUrl);
          this.slot.imagepath = imageUrl;  
          this.addSlot();
        },
        error: (error) => {
          console.error(error);
          this.message = 'Image upload failed!';
        }
      });
    }
  }

  addSlot(): void {
    this.adminService.addSlot(this.slot).subscribe({
      next: () => {
        this.message = "Slot added successfully!";
        console.log("Slot added");
      },
      error: (error) => {
        console.error(error);
        this.message = "Failed to add slot!";
      }
    });
  }
}
