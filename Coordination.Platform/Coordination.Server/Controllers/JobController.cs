using Coordination.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace Coordination.Server.Controllers
{
    public class JobController : BaseController
    {

        //
        // GET: /Default1/

        public ActionResult Index()
        {
            return View(db.Jobs.Where(job => job.Completed == false).ToList());
        }

        [HttpGet]
        public ActionResult Create(int? id)
        {
            return View();
        }

        // POST: /Job/Create
        [HttpPost]
        public ActionResult Create()
        {
            string jobName = Request["JobName"];
            string description = Request["Description"];
            string latitude = Request["Latitude"];
            string longitude = Request["Longitude"];

            Job job = new Job();
            job.JobName = jobName;
            job.Description = description;
            job.Latitude = latitude;
            job.Longitude = longitude;

            job.Completed = false;

            db.Jobs.Add(job);
            db.SaveChanges();

            return RedirectToAction("Index");
        }

        // GET: /Job/Edit/5
        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Job job = db.Jobs.FirstOrDefault(jb => jb.ID == id);
            if (job == null)
            {
                return HttpNotFound();
            }
            return View(job);
        }

        // POST: /Movies/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        public ActionResult Edit()
        {
            string jobname = Request["JobName"];
            string description = Request["Description"];
            int? id = int.Parse(Request["ID"]);
            string latitude = Request["Latitude"];
            string longitude = Request["Longitude"];

            Job job = db.Jobs.FirstOrDefault(jb => jb.ID == id);
            if (job == null)
            {
                return HttpNotFound();
            }
            job.JobName = jobname;
            job.Description = description;
            job.Latitude = latitude;
            job.Longitude = longitude;

            db.Entry(job).State = System.Data.Entity.EntityState.Modified;
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int? id)
        {
            try
            {
                Job job = db.Jobs.FirstOrDefault(x => x.ID == id);
                if (job == null)
                {
                    return HttpNotFound();
                }

                db.Jobs.Remove(job);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                ViewBag.ErrorMessage = "You cannot delete a job assigned to a user!";
                return View("Index", db.Jobs.Where(job => job.Completed == false).ToList());
            }
        }
        

        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            Model.Job job = db.Jobs.FirstOrDefault(x => x.ID == id);
            if (job == null)
            {
                return HttpNotFound();
            }
            return View(job);

        }

    }
}
