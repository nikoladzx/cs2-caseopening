import { Component, OnInit } from '@angular/core';
import { Case } from 'src/app/models/Case';
import { AdminService } from 'src/app/services/admin/admin.service';
import { ImgService } from 'src/app/services/img/img.service';

@Component({
  selector: 'app-admin-case',
  templateUrl: './admin-case.component.html',
  styleUrls: ['./admin-case.component.css']
})
export class AdminCaseComponent implements OnInit {

     case: Case = { name: '', price: 0, img: '', id: 0, items: [] };
     selectedFile: File | null = null;
     message: string | null = null;
     slotId: string | null = null;
   
     constructor(private adminService: AdminService, private imgService: ImgService) { }
   
     ngOnInit(): void {
      }
   
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
             this.case.img = imageUrl;  
             this.addCaseItem();
           },
           error: (error) => {
             console.error(error);
             this.message = 'Image upload failed!';
           }
         });
       }
     }
   
     addCaseItem(): void {
       this.adminService.addCase(this.case).subscribe({
         next: () => {
           this.message = "Case added successfully!";
           console.log("Case added");
         },
         error: (error) => {
           console.error(error);
           this.message = "Failed to add case!";
         }
       });
     }
}
