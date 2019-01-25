using Coordination.Model;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Coordination.Server.EntityFramework
{
    public class CoordinationDbContext : DbContext
    {
        public DbSet <User> Users { get; set; }
        public DbSet<Job> Jobs { get; set; }
        public DbSet<JobAssignment> JobAssignments { get; set; }
    }

}