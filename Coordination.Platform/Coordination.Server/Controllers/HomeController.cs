using Coordination.Server.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Coordination.Server.Controllers
{
    public class HomeController : BaseController
    {
         
        public ActionResult Index()
        {
            ViewBag.Message = "Modify this template to jump-start your ASP.NET MVC application.";                       
            return View();
        }

        public ActionResult Map()
        {
            HomeMapViewModel model = new HomeMapViewModel();
            model.Jobs = db.Jobs.Where(job=>job.Completed==false).ToList();
            model.Users = db.Users.ToList();
            return PartialView(model);
        }
        public ActionResult Chat()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your app description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
        public JsonResult GetMarkers()
        {
            return Json(new {Name="Hikaku" });

        }
        public JsonResult isAssignmentsUpdated()
        {
            
            return Json(new { mustUpdate = true });

        }
        public ActionResult AssignmentsList()
        {
        
            return PartialView(db.JobAssignments.Where(ja => ja.Approved==false));
        }
        public JsonResult AssignmentAction(bool approved,int assignmentID)
        {
            Model.JobAssignment ja = db.JobAssignments.FirstOrDefault(x => x.ID == assignmentID);
            if (approved)
            {
                ja.Approved = approved;
                db.Entry(ja).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                
            }
            else
            {
                db.JobAssignments.Remove(ja);
                db.SaveChanges();
            }
            return Json(true);




        }
        public ActionResult ApprovedAssignments()
        {
            return PartialView(db.JobAssignments.Where(ja => ja.Approved && ja.Job.Completed==false));
        }
    }

}
