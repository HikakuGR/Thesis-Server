namespace Coordination.Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class assignedRemoved : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.Jobs", "Assigned");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Jobs", "Assigned", c => c.Boolean());
        }
    }
}
